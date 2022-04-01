/*
 * $Id$
 * $URL$
 */
package email.relay.smtp;


public class Version
{
	public static String getSpecification()
	{
		Package pkg = Version.class.getPackage();
		return (pkg == null) ? "1.0" : pkg.getSpecificationVersion();
	}
	public static String getImplementation()
	{
		Package pkg = Version.class.getPackage();
		return (pkg == null) ? null : pkg.getImplementationVersion();
	}

	public static void main(String[] args)
	{
		System.out.println("Version: " + getSpecification());
		System.out.println("Implementation: " + getImplementation());
	}
}
