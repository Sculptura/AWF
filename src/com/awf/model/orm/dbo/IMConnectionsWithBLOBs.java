package com.awf.model.orm.dbo;

public class IMConnectionsWithBLOBs extends IMConnections {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.IMConnections.User1Address
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    private String user1address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dbo.IMConnections.User2Address
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    private String user2address;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.IMConnections.User1Address
     *
     * @return the value of dbo.IMConnections.User1Address
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    public String getUser1address() {
        return user1address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.IMConnections.User1Address
     *
     * @param user1address the value for dbo.IMConnections.User1Address
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    public void setUser1address(String user1address) {
        this.user1address = user1address == null ? null : user1address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dbo.IMConnections.User2Address
     *
     * @return the value of dbo.IMConnections.User2Address
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    public String getUser2address() {
        return user2address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dbo.IMConnections.User2Address
     *
     * @param user2address the value for dbo.IMConnections.User2Address
     *
     * @mbg.generated Wed Dec 12 10:36:58 GST 2018
     */
    public void setUser2address(String user2address) {
        this.user2address = user2address == null ? null : user2address.trim();
    }
}