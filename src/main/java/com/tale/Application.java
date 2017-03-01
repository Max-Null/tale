package com.tale;

<<<<<<< 60abd17a13b4badda4bbb9894bb207c3afb281ba
import com.blade.config.BConfig;
import com.blade.kit.FileKit;
import com.tale.controller.admin.AttachController;
=======
import com.tale.init.TaleLoader;
import com.tale.utils.ExtClasspathLoader;
>>>>>>> 🦄 fix plugin code

import java.io.File;

import static com.blade.Blade.$;

public class Application {

<<<<<<< 60abd17a13b4badda4bbb9894bb207c3afb281ba
    public static void main(String[] args) {
        BConfig bConfig = $().bConfig();
        String themeDir = AttachController.CLASSPATH + "templates/themes";
        File[] dir = new File(themeDir).listFiles();
        for(File f : dir){
            if(f.isDirectory() && FileKit.isDirectory(f.getPath() + "/static")){
                bConfig.addStatic(new String[]{"/templates/themes/"+ f.getName() +"/static"});
            }
        }
=======
    public static void main(String[] args) throws Exception {
        TaleLoader.init();
        TaleLoader.loadPlugin(new File("/Users/biezhi/workspace/projects/java/plugin_upyun/target/plguin_upyun.jar"));
>>>>>>> 🦄 fix plugin code
        $().start(Application.class);
    }

}