package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.Question;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class QuestionDao {
	private static QuestionDao questionDao;
	private JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance();
	private QuestionDao() { }
	public static QuestionDao getInstance() {
		if(questionDao == null) {
			questionDao = new QuestionDao();
		}
		return questionDao;
	}
	
    public List<Question> findAll() {
        String sql = "SELECT questionId, writer, title, createdDate, countOfAnswer FROM QUESTIONS "
                + "order by questionId desc";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), null,
                        rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }

        };

        return jdbcTemplate.query(sql, rm);
    }

    public Question findById(long questionId) {
        String sql = "SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS "
                + "WHERE questionId = ?";

        RowMapper<Question> rm = new RowMapper<Question>() {
            @Override
            public Question mapRow(ResultSet rs) throws SQLException {
                return new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
            }
        };

        return jdbcTemplate.queryForObject(sql, rm, questionId);
    }

	public void updateCountOfAnswer(long questionId, int i) {
		// TODO Auto-generated method stub
		String sql = "UPDATE QUESTIONS" +
	             " set countOfAnswer = countOfAnswer" + (i > 0 ? " + "+i : " " + i ) +
			     " WHERE questionId = ?";
	jdbcTemplate.update(sql, questionId);
	}
	public void insert(Question question) throws SQLException{
		String sql = "INSERT INTO QUESTIONS (writer, title, contents, createdDate, countOfAnswer)" +
		 " VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),question.getCreatedDate(), question.getCountOfComment());
	}
	
	public void delete(Long questionId) throws SQLException{
		String sql = "DELETE FROM QUESTIONS WHERE questionId=?";
		jdbcTemplate.update(sql, questionId);
	}
	
	public void update(Question question) throws SQLException{
		String sql = "UPDATE QUESTIONS SET" +
				" writer=?, title=?, contents=?, createdDate=?, countOfAnswer=?" +
				" WHERE questionId=?";
		jdbcTemplate.update(sql, question.getWriter(), question.getTitle(), question.getContents(),question.getCreatedDate(), question.getCountOfComment(), question.getQuestionId());
	}
}
