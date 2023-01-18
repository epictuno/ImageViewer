package imageviewer;

import imageviewer.architecture.CurrentCommand;
import imageviewer.architecture.Image;
import imageviewer.architecture.ImagePresenter;
import imageviewer.architecture.NextCommand;
import imageviewer.architecture.PrevCommand;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        MainFrame frame = MainFrame.create();
        Image image = new FileImageLoader(new File("images")).load();
        ImagePresenter presenter = ImagePresenter.with(frame.imageDisplay());
        frame.add("next", new NextCommand(presenter));
        frame.add("prev", new PrevCommand(presenter));
        frame.add("refresh",new CurrentCommand(presenter));
        frame.start();
        presenter.show(image);
    }
    
}
