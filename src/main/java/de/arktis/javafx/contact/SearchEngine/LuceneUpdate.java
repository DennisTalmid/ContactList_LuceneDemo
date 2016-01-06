package de.arktis.javafx.contact.SearchEngine;

import de.arktis.javafx.contact.ContactMain;
import de.arktis.javafx.contact.controller.PersonOverviewController;
import de.arktis.javafx.contact.model.Person;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import java.io.IOException;

public class LuceneUpdate {

    private IndexWriter indWriter;
    private PersonOverviewController persOvCtrl;
    private ContactMain contactMain;
    private LuceneIndexSearcher searcherInstance = new LuceneIndexSearcher();
    private LuceneIndex indexInstance = new LuceneIndex();

    //TODO Update-Methode fertigstellen
    public void updateIndex() throws IOException {
        Document doc;
        indWriter = indexInstance.getIndWriter();
        System.out.println("updating...\n");
        doc = null;
        indWriter.addDocument(doc);
        persOvCtrl = new PersonOverviewController();
        Person found = persOvCtrl.getFoundPerson();
        contactMain.getPersonData().add(found);
        Document addedDoc = searcherInstance.getReader().document(0);
        addedDoc.add(new Field("title", found.getFirstName() + " "
                + found.getLastName(), Field.Store.YES,
                Field.Index.ANALYZED));
        indWriter.tryDeleteDocument(searcherInstance.getReader(), indexInstance.getDocID());
        indWriter.updateDocument(new Term("title", "test"), doc);
        indWriter.close();
    }
}
