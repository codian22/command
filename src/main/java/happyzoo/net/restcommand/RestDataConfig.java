package happyzoo.net.restcommand;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.io.File;
import java.util.*;

public class RestDataConfig {
    private RestCommandData restData = new RestCommandData();


}

class RestCommandData {
    private Map header          = null;
    private Map apiMap          = null;
    private String base_url     = null;
    private Map optons          = null;
    private String currentPath  = "";
    private List configFiles    = null;
    private JsonNode rootNode   = null;

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    public String getCurrentPath() {
        return this.currentPath;
    }


    public JsonNode getRootNode() {
        return rootNode;
    }

    public RestCommandData()
    {
        // 1) Set currentPath
        this.currentPath = System.getProperty("user.dir");

        // 2) Get and Save the config files
        for(String fileName : new File(this.currentPath).list())
        {
            File file = new File(this.currentPath + "/" + fileName);
            if( fileName.toLowerCase().endsWith(".json") && file.isFile())
            {
                this.configFiles = (this.configFiles == null)? new ArrayList() : this.configFiles;
                this.configFiles.add(this.currentPath + "\\" + file.getName());
            }
        }

        // 3) select default file :
        if(this.configFiles != null && this.configFiles.size() > 0)
        {
            this.rootNode = JsonUtil.createJsonRootNode( (String)this.configFiles.get(0));
            if(this.rootNode != null)
            {
                ObjectMapper mapper = new ObjectMapper();
                this.header = mapper.convertValue(this.rootNode.get("header"), Map.class);
                this.apiMap = mapper.convertValue(this.rootNode.get("api"), Map.class);
                this.optons = mapper.convertValue(this.rootNode.get("options"), Map.class);
            }
        }
    }

//    private static final String STR_FORMAT_20S_S = "%20s : %s";

    public List getConfigFiles()
    {
        return  this.configFiles;
    }

    public void displayStatus()
    {
        String message = "";
        message += String.format("%20s : %s\n", "Current Directory", this.currentPath);
        message += String.format("%20s : %s\n", "Files Count", this.getConfigFiles().size());

        for(int i = 0; i < this.getConfigFiles().size(); i++)
        {
            message += String.format("\t[%3d] %20s \n", i + 1 , this.getConfigFiles().get(i));
        }

        message += String.format("%-20s : %s\n", "Root Node", this.rootNode);
        message += String.format("%-20s : %s\n", "Json (header)", this.rootNode.get("header"));
        message += String.format("%-20s : %s\n", "Json (api)", this.rootNode.get("api"));
        message += String.format("%-20s : %s\n", "Json (options)", this.rootNode.get("options"));

        System.out.println(message);
    }

}
