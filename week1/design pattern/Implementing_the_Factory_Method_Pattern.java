// Interface representing a document with basic operations
interface FileDocument {
    void open();
    void close();
}

// Implementation for handling Word documents
class WordFile implements FileDocument {
    @Override
    public void open() {
        System.out.println("Opening Word file...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Word file...");
    }
}

// Implementation for handling PDF documents
class PdfFile implements FileDocument {
    @Override
    public void open() {
        System.out.println("Opening PDF file...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing PDF file...");
    }
}

// Implementation for handling Excel documents
class ExcelFile implements FileDocument {
    @Override
    public void open() {
        System.out.println("Opening Excel file...");
    }
    
    @Override
    public void close() {
        System.out.println("Closing Excel file...");
    }
}

// Abstract factory class for creating document objects
abstract class DocumentCreator {
    public abstract FileDocument createFileDocument();
}

// Concrete factory for creating Word document instances
class WordFileFactory extends DocumentCreator {
    @Override
    public FileDocument createFileDocument() {
        return new WordFile();
    }
}

// Concrete factory for creating PDF document instances
class PdfFileFactory extends DocumentCreator {
    @Override
    public FileDocument createFileDocument() {
        return new PdfFile();
    }
}

// Concrete factory for creating Excel document instances
class ExcelFileFactory extends DocumentCreator {
    @Override
    public FileDocument createFileDocument() {
        return new ExcelFile();
    }
}

// Demonstration of the Factory Method Pattern
class FactoryMethodPatternDemo {
    public static void main(String[] args) {
        // Create and use a Word document
        DocumentCreator wordFactory = new WordFileFactory();
        FileDocument wordFile = wordFactory.createFileDocument();
        wordFile.open();
        wordFile.close();

        // Create and use a PDF document
        DocumentCreator pdfFactory = new PdfFileFactory();
        FileDocument pdfFile = pdfFactory.createFileDocument();
        pdfFile.open();
        pdfFile.close();

        // Create and use an Excel document
        DocumentCreator excelFactory = new ExcelFileFactory();
        FileDocument excelFile = excelFactory.createFileDocument();
        excelFile.open();
        excelFile.close();
    }
}
