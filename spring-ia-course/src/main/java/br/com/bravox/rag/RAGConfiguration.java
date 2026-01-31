package br.com.bravox.rag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Paths;

@Configuration
public class RAGConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(RAGConfiguration.class);
    private static final String vectorStoreName = "vectorstore.json";

    @Value("classpath:/data/models.json")
    private Resource models;

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel){
        var simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        var vectorStoreFile  = getVectorStoreFile();
        if(vectorStoreFile.exists()){
            logger.info("Loading vector store from file: {}", vectorStoreFile.getAbsolutePath());
            simpleVectorStore.load(vectorStoreFile);
        }else {
            logger.info("Creating vector store file: {}", vectorStoreFile.getAbsolutePath());
            var textReader = new TextReader(models);
            textReader.getCustomMetadata().put("filename", "models.json");
            var documents = textReader.get();
            var tokenTextSplitter = new TokenTextSplitter();
            var splitDocuments = tokenTextSplitter.apply(documents);
            simpleVectorStore.add(splitDocuments);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }

    private File getVectorStoreFile(){
        var path = Paths.get("src", "main", "resources", "data");
        var absolutePath = path.toAbsolutePath() + "/" + vectorStoreName;
        return new File(absolutePath);
    }
}
