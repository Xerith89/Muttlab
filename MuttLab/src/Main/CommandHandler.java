package Main;

import java.util.*;

/* 
* This class is responsible for creating menu commands and
* handling them by utilising polymorphism - calling the
* executeCommand function depending on whether the string 
* contains a command only or command and argument. It also populates the
* command list based on whether this is the pro or free version
*/

public class CommandHandler
{
    static Map<String, CommandInterface> commsMap = new HashMap();
    Commands commands;
    TextHandler text;
                
    public CommandHandler()
    {
        commands = new Commands();
        text = new TextHandler();  
        
        //Simple if check on version type and calls the right function to populate the list
        if (MuttLab.checkProVersion())
        {
           proCommands(); 
        }
        else
        {
           basicCommands(); 
        }
    }
                
    public void executeCommands(String[] commAndArg) 
    {  
        //check if the command is a key in the command map 
        if (commsMap.containsKey(commAndArg[0])) 
        {
            //the value of the key, which will be an object that implements CommandInterface
            CommandInterface eCommand = commsMap.get(commAndArg[0]);
            //Check if we have an argument to process or not
            if (commAndArg.length > 1)
            {
                eCommand.executeCommand(Optional.ofNullable(commAndArg[1])); //Execute command with arg
            }
            else
            {
                eCommand.executeCommand(Optional.empty()); //Execute without arg
            }
        }
        else
        {  
            //else the command is not in our map so print a message
            text.printLine("Unknown Command");
        }
    } 
    
    //These two functions fill the map with commands depending on the version
    private void proCommands()
    {               
        commsMap.put("dup", commands.new DupeMatrix());
        commsMap.put("[", commands.new NewMatrix());
        commsMap.put("+", commands.new AddMatrix());
        commsMap.put("-", commands.new SubtractMatrix());
        commsMap.put("*", commands.new MultiplyMatrix());
        commsMap.put(".*", commands.new MultiplyByElements());
        commsMap.put("save", commands.new SaveMatrix());
        commsMap.put("help", commands.new ShowHelp());
        commsMap.put("quit", commands.new QuitProgram());
        commsMap.put("script", commands.new Script());
        commsMap.put("show", commands.new ShowMatrices());
        commsMap.put("clone", commands.new CloneMatrix());
    }
    
    private void basicCommands()
    {
        commsMap.put("dup", commands.new DupeMatrix());
        commsMap.put("[", commands.new NewMatrix());
        commsMap.put("+", commands.new AddMatrix());
        commsMap.put("-", commands.new SubtractMatrix());
        commsMap.put("*", commands.new MultiplyMatrix());
        commsMap.put(".*", commands.new MultiplyByElements());
        commsMap.put("save", commands.new SaveMatrix());
        commsMap.put("help", commands.new ShowHelp());
        commsMap.put("quit", commands.new QuitProgram());
    }
}
