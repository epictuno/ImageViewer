package imageviewer.architecture;

public class CurrentCommand implements Command{
    private final ImagePresenter presenter;

    public CurrentCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }
    
    @Override
    public void execute() {
        presenter.show(presenter.current());
    }  
}
