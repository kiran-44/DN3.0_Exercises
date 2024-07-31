interface Picture {
    void show();
}

// Concrete class implementing the real image loading
class ActualImage implements Picture {
    private String filePath;

    public ActualImage(String filePath) {
        this.filePath = filePath;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading image from disk: " + filePath);
    }

    @Override
    public void show() {
        System.out.println("Displaying image: " + filePath);
    }
}

// Proxy class that controls access to the actual image
class ImageProxy implements Picture {
    private String filePath;
    private ActualImage actualImage;

    public ImageProxy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void show() {
        if (actualImage == null) {
            actualImage = new ActualImage(filePath);
        }
        actualImage.show();
    }
}

// Main class to test the proxy pattern
class ProxyPatternDemo {
    public static void main(String[] args) {
        Picture image1Proxy = new ImageProxy("image1.jpg");
        Picture image2Proxy = new ImageProxy("image2.jpg");

        // Image is loaded from disk for the first time
        image1Proxy.show();
        System.out.println("");

        // Image is not loaded from disk as it is already loaded
        image1Proxy.show();
        System.out.println("");

        // Image is loaded from disk for the first time
        image2Proxy.show();
        System.out.println("");

        // Image is not loaded from disk as it is already loaded
        image2Proxy.show();
    }
}
