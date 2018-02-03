package happyzoo.net.restcommand;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RestDataConfigTest {

    private RestCommandData rcd = new RestCommandData();

    @Test
    public void currentDirectory()
    {
        this.rcd = new RestCommandData();
        assertEquals("D:\\200. DevSpace\\WorkSpace\\IdeaProjects\\rest-command", rcd.getCurrentPath());
    }

    @Test
    public void getConfigFiles()
    {
        List<String> fileList = rcd.getConfigFiles();
        assertEquals(1, fileList.size());

        rcd.displayStatus();

        for(int i =0; i < fileList.size(); i++)
        {
            JsonNode node = JsonUtil.createJsonRootNode( fileList.get(i));
            System.out.println(node);
        }
    }

    @Test
    public void getDefaultConfigFile()
    {
        List fileList = rcd.getConfigFiles();
        assertEquals(1, fileList.size());

        assertNotEquals(null, rcd.getRootNode());
        System.out.println(rcd.getRootNode().getNodeType());
        rcd.displayStatus();

    }
}
