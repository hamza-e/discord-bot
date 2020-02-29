package commands;

import java.lang.reflect.Method;

import commands.Command.ExecuterType;

public final class SimpleCommand {
	private final String name,description;
	private final ExecuterType executerType;
	private final Object object;
	private final Method method;
	public SimpleCommand(String name, String description, ExecuterType executerType, Object object, Method method) {
		super();
		this.name = name;
		this.description = description;
		this.executerType = executerType;
		this.object = object;
		this.method = method;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public ExecuterType getExecuterType() {
		return executerType;
	}
	public Object getObject() {
		return object;
	}
	public Method getMethod() {
		return method;
	}
	
	
}
