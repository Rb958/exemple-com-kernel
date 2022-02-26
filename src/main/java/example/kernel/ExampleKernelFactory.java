package example.kernel;

public class ExampleKernelFactory {

    private static ExampleKernel kernel;

    public static ExampleKernel getInstance() {
        if (kernel == null){
            kernel = new ExampleKernel();
        }
        return kernel;
    }
}
