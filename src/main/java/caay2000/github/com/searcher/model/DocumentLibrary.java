package caay2000.github.com.searcher.model;

import java.util.HashSet;
import java.util.Set;

public class DocumentLibrary {

    private final Set<Document> documents;

    public DocumentLibrary() {
        this.documents = new HashSet<>();
    }

    public void addDocument(Document document) {
        this.documents.add(document);
    }

    public int size() {
        return this.documents.size();
    }

    public Set<Document> getDocuments() {
        return documents;
    }
}
