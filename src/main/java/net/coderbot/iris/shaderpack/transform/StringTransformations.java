package net.coderbot.iris.shaderpack.transform;

public class StringTransformations implements Transformations {
	private String prefix;
	private StringBuilder injections;
	private String body;

	public StringTransformations(String base) {
		int versionStringStart = base.indexOf("#version");

		if (versionStringStart == -1) {
			throw new IllegalArgumentException("A valid shader should include a version string");
		}

		String prefix = base.substring(0, versionStringStart);
		base = base.substring(versionStringStart);

		int splitPoint = base.indexOf("\n") + 1;

		this.prefix = prefix + base.substring(0, splitPoint);
		this.injections = new StringBuilder();
		this.body = base.substring(splitPoint);
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean contains(String content) {
		return toString().contains(content);
	}

	@Override
	public void injectLine(InjectionPoint at, String line) {
		if (at == InjectionPoint.AFTER_VERSION) {
			injections.append(line);
			injections.append('\n');
		} else {
			throw new IllegalArgumentException("Unsupported injection point: " + at);
		}
	}

	@Override
	public void replaceExact(String from, String to) {
		if (from.contains("\n")) {
			// Block newline replacements for now, since that could mean that we're trying to replace across
			// injections / body and that will result in weird behavior.
			throw new UnsupportedOperationException();
		}

		prefix = prefix.replace(from, to);
		injections = new StringBuilder(injections.toString().replace(from, to));
		body = body.replace(from, to);
	}

	@Override
	public String toString() {
		return prefix + injections + body;
	}
}