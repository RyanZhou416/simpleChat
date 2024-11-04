// 本文件包含支持教科书第3.7节的材料：
// "面向对象软件工程"，并根据www.lloseng.com上的开源许可证发布

package edu.seg2105.client.backend;

import ocsf.client.*;

import java.io.*;

import edu.seg2105.client.common.*;

/**
 * 这个类重写了抽象超类中定义的一些方法，以便为客户端提供更多功能。
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 */
public class ChatClient extends AbstractClient
{
  //实例变量 **********************************************
  
  /**
   * 接口类型变量。它允许在客户端中实现display方法。
   */
  ChatIF clientUI; 

  
  //构造函数 ****************************************************
  
  /**
   * 构造一个聊天客户端实例。
   *
   * @param host 要连接的服务器。
   * @param port 要连接的端口号。
   * @param clientUI 接口类型变量。
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //调用超类构造函数
    this.clientUI = clientUI;
    openConnection();
  }

  
  //实例方法 ************************************************
    
  /**
   * 这个方法处理从服务器传入的所有数据。
   *
   * @param msg 来自服务器的消息。
   */
  public void handleMessageFromServer(Object msg) 
  {
    clientUI.display(msg.toString());
    
    
  }

  /**
   * 这个方法处理来自UI的所有数据            
   *
   * @param message 来自UI的消息。    
   */
  public void handleMessageFromClientUI(String message)
  {
    try
    {
      sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("无法发送消息到服务器。终止客户端。");
      quit();
    }
  }
  
  /**
   * 这个方法终止客户端。
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
// ChatClient类结束
