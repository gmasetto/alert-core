package com.involves.selecao.gateway;

import java.util.ArrayList;
import java.util.List;

import com.involves.selecao.alerta.TipoAlerta;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.alerta.Alerta;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

@Component
public class AlertaMongoGateway implements AlertaGateway {
	
	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		
		Document doc = new Document(
				"ponto_de_venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", alerta.getFlTipo())
                .append("margem", alerta.getMargem())
                .append("produto", alerta.getProduto());

		collection.insertOne(doc);
	}
	
	@Override
	public long alertasTotalizador() {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		return collection.count();
	}

	@Override
	public TipoAlerta saveTipoAlerta(TipoAlerta tipoAlerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("TipoAlerta");

		Document doc = new Document(
				"nome", tipoAlerta.getNome())
				.append("descricao", tipoAlerta.getDescricao());
		collection.insertOne(doc);

			return tipoAlerta;
	}

	@Override
	public TipoAlerta buscarTipoAlerta(String nome) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("TipoAlerta");
		Bson query = eq("nome", nome);
		TipoAlerta tipoAlerta = null;

		FindIterable<Document> list = collection.find(query);
		for (Document doc : list) {
			tipoAlerta = new TipoAlerta();
			tipoAlerta.setNome(doc.getString("nome"));
			tipoAlerta.setDescricao("descricao");
		}

		return tipoAlerta;
	}

	@Override
	public List<Alerta> buscar(int page, int size) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
        
        List<Document> results = pagination(collection, page, size);       
		
		List<Alerta> alertas = new ArrayList<>();
		for (Document document : results) {
			Alerta alerta = new Alerta();
			alerta.setDescricao(document.getString("descricao"));
			alerta.setFlTipo(document.getInteger("tipo"));
			alerta.setMargem(document.getInteger("margem"));
			alerta.setPontoDeVenda(document.getString("ponto_de_venda"));
			alerta.setProduto(document.getString("produto"));
			alertas.add(alerta);
		}
		
		return alertas;
	}
	
    public List<Document> pagination(MongoCollection <Document> document, int page, int size) { 
    	List<Document> results = null;
    	try {
            MongoCursor <Document> cursor = document.find().skip(size * (page - 1)).limit(size).iterator();
            results = new ArrayList<>();
            
            while (cursor.hasNext()) {
                Document result = cursor.next();
                results.add(result);
            }
            cursor.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;
        
    }
}
