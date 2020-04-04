package org.bkatwal.parkinglot.services;

import java.util.Arrays;
import org.bkatwal.parkinglot.api.CommandExecutor;
import org.bkatwal.parkinglot.api.CommandExhibitor;
import org.bkatwal.parkinglot.api.CommandParser;
import org.bkatwal.parkinglot.api.ProcessorTemplate;
import org.bkatwal.parkinglot.core.ServiceLocator;
import org.bkatwal.parkinglot.exceptions.ParkinglotException;
import org.bkatwal.parkinglot.utils.CommandEnum;
import org.bkatwal.parkinglot.utils.ParkingLotUtils;

public class CommandProcessorTemplate implements ProcessorTemplate {

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public String process(final String command) {
    try {
      if (command == null) {
        throw new ParkinglotException("Invalid command");
      }

      String[] commandArr = ParkingLotUtils.getCommandTokens(command);
      CommandEnum commandEnum = CommandEnum.getCommandByName(commandArr[0]);

      CommandParser commandParser = ServiceLocator.getInstance()
          .getService(commandEnum.getParserName(), CommandParser.class);

      CommandExecutor commandExecutor = ServiceLocator.getInstance()
          .getService(commandEnum.getExecutorName(), CommandExecutor.class);

      CommandExhibitor commandExhibitor = ServiceLocator.getInstance()
          .getService(commandEnum.getExhibitorName(), CommandExhibitor.class);

      Object parsedParam = commandParser
          .parse(Arrays.copyOfRange(commandArr, 1, commandArr.length));
      Object commandOutput = commandExecutor.execute(parsedParam);
      return commandExhibitor.exhibit(commandOutput);
    } catch (ParkinglotException e) {
      return e.getMessage();
    }
  }
}
