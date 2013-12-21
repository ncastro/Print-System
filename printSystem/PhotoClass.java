package printSystem;

public class PhotoClass implements Photo{
	
	static final long serialVersionUID = 0L;

	private String code;
	private int width, heith;
	private String file;
	
	public PhotoClass(String code, int width, int heith, String file) {
		this.code = code;
		this.width = width;
		this.heith = heith;
		this.file = file;
	}
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getheight() {
		return heith;
	}

	@Override
	public String savedFile() {
		return file;
	}
	
}
