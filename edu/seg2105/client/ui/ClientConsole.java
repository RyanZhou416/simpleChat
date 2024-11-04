package edu.seg2105.client.ui;
// 本文件包含支持教科书第3.7节的材料：
// "面向对象软件工程"，并根据www.lloseng.com上的开源许可证发布

import java.io.*;
import java.util.Scanner;

import edu.seg2105.client.backend.ChatClient;
import edu.seg2105.client.common.*;

/**
 * 这个类构建了一个聊天客户端的UI。它实现了
 * 聊天接口以激活display()方法。
 * 警告：这里的一些代码在ServerConsole中被克隆
 *
 * 作者 Fran&ccedil;ois B&eacute;langer
 * 作者 Dr Timothy C. Lethbridge  
 * 作者 Dr Robert Lagani&egrave;re
 */
public class ClientConsole implements ChatIF 
{
  //类变量 *************************************************
  
  /**
   * 默认连接端口。
   */
  final public static int DEFAULT_PORT = 5555;
  
  //实例变量 **********************************************
  
  /**
   * 创建此ConsoleChat的客户端实例。
   */
  ChatClient client;
  
  /**
   * 从控制台读取的扫描器
   */
  Scanner fromConsole; 

  //构造函数 ****************************************************

  /**
   * 构造ClientConsole UI的实例。
   *
   * @param host 要连接的主机。
   * @param port 要连接的端口。
   */
  public ClientConsole(String host, int port) 
  {
    try 
    {
      client= new ChatClient(host, port, this);
    } 
    catch(IOException exception) 
    {
      System.out.println("错误：无法建立连接！"
                + " 终止客户端。");
      System.exit(1);
    }
    
    // 创建扫描器对象以从控制台读取
    fromConsole = new Scanner(System.in); 
  }

  //实例方法 ************************************************
  
  /**
   * 该方法等待来自控制台的输入。一旦收到，
   * 它将其发送到客户端的消息处理程序。
   */
  public void accept() 
  {
    try
    {
      String message;

      while (true) 
      {
        message = fromConsole.nextLine();
        client.handleMessageFromClientUI(message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println("从控制台读取时发生意外错误！");
    }
  }

  /**
   * 该方法覆盖了ChatIF接口中的方法。它
   * 在屏幕上显示一条消息。
   *
   * @param message 要显示的字符串。
   */
  public void display(String message) 
  {
    System.out.println("> " + message);
  }

  //类方法 ***************************************************
  
  /*
   * 该方法负责创建客户端UI。
   *
   * @param args[0] 要连接的主机。
   */
  public static void main(String[] args) 
  {
    String host = "";

    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    ClientConsole chat= new ClientConsole(host, DEFAULT_PORT);
    chat.accept();  //等待控制台数据
  }
}
// ConsoleChat类结束
