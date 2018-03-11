package shabtay.coupon.system.connection;

import java.util.ArrayList;

/**
 * Connection pool holding 5 connections
 * @author Shabtay Shalem
 *
 */
public class ConnectionPool {

	private static ConnectionPool _INSTANCE;

	// define maximum 5 connections
	private static final int NUMBER_OF_CONNECTIONS = 5;

	// list of connections
	private ArrayList<DBConnection> connections = null;

	/*
	 * Initializing connectionPool with 5 connections
	 */
	private ConnectionPool() {
		connections = new ArrayList<>();
		for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
			DBConnection dbConnection = new DBConnection();
			connections.add(dbConnection);
		}
	}

	/**
	 * Returning INSTANCE connection
	 * this is singleton since only one instance of Connection pool is created 
	 * @return ConnectionPool instance
	 */
	public static synchronized ConnectionPool getInstance() {
		if (_INSTANCE == null) 
		{
			_INSTANCE = new ConnectionPool();
		}
		return _INSTANCE;
	}
	
	
	/**
	 * get a connection from the list
	 * if there is no connection available- wait
	 * @return DBConnection
	 * @throws InterruptedException
	 */
    public synchronized DBConnection getConnection() throws InterruptedException {
        while (this.connections.size() == 0) {          
            wait();
        }
        DBConnection connection = this.connections.get(0);
        this.connections.remove(0);        
        return connection;
    }

    /**
     *  return connection into the list- notify (the waiters)
     * @param connection
     */
    public synchronized void returnConenction(DBConnection connection)
    {
        this.connections.add(connection);        
        notify();
    }

	

	
}
