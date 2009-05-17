package kentaro714.documents.domain;

public interface Document {
	public void proceed(ValidationErrors errors);
	public void reject(ValidationErrors errors);
	public void delete(ValidationErrors errors);
}
