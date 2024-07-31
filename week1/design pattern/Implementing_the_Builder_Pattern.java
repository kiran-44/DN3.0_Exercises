class BuilderPatternDemo {
    // Represents a Computer with various hardware components
    static class Computer {
        private String processor;
        private String memory;
        private String diskSpace;

        // Private constructor to ensure object creation is controlled via Builder
        private Computer(ComputerBuilder builder) {
            this.processor = builder.processor;
            this.memory = builder.memory;
            this.diskSpace = builder.diskSpace;
        }

        // Static nested Builder class for constructing Computer objects
        public static class ComputerBuilder {
            private String processor;
            private String memory;
            private String diskSpace;

            // Sets the processor type and returns the Builder for chaining
            public ComputerBuilder setProcessor(String processor) {
                this.processor = processor;
                return this;
            }

            // Sets the amount of memory and returns the Builder for chaining
            public ComputerBuilder setMemory(String memory) {
                this.memory = memory;
                return this;
            }

            // Sets the disk space and returns the Builder for chaining
            public ComputerBuilder setDiskSpace(String diskSpace) {
                this.diskSpace = diskSpace;
                return this;
            }

            // Builds and returns a Computer instance
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // Demonstrates the use of the Builder pattern to create a Computer object
static void main(String[] args) {
        // Construct a high-performance gaming PC using the Builder
        Computer gamingPC = new Computer.ComputerBuilder()
                .setProcessor("Intel Core i9")
                .setMemory("32GB")
                .setDiskSpace("1TB SSD")
                .build();

        // Output the specifications of the gaming PC
        System.out.println("Processor: " + gamingPC.processor);
        System.out.println("Memory: " + gamingPC.memory);
        System.out.println("Disk Space: " + gamingPC.diskSpace);
    }
}
