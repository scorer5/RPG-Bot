import sx.blah.discord.handle.obj.IUser;

public class Group {
	private static final int GROUP_SIZE = 4;
	private IUser[] members = new IUser[GROUP_SIZE];
	
	/**
	 * Creates new group with a leader and a member
	 * @param leader leader of group
	 * @param member first member of group
	 */
	public Group(IUser leader, IUser member) {
		members[0] = leader;
	}
	
	/**
	 * Deletes the group
	 */
	public void disband() {
		members = new IUser[0];
	}
	
	/**
	 * Adds a Member to the group
	 * @param member the new member to be added
	 * @return true, if the member could be added, false, if there wasn't enough space in the group
	 */
	public boolean addMember(IUser member) {
		int tempCounter = 0;
		for(int i = 0; i < members.length; i++) {
			if(members[i] == null) {
				tempCounter++;
			}
		}
		
		if(tempCounter == 0) {
			return false;
		}else {
			for(int i = 0; i < members.length; i++) {
				if(members[i] != null) {
					members[i] = member;
					break;
				}
			}
		}
		return true;
	}
	
	/**
	 * The members of the group
	 * @return all members without the leader
	 */
	public IUser[] getMembers() {
		IUser[] tempArr = new IUser[GROUP_SIZE-1];
		for(int i = 0; i < tempArr.length; i++) {
			tempArr[i] = members[i+1];
		}		
		return tempArr;
	}
	
	/**
	 * Leader of the group
	 * @return leader of the group
	 */
	public IUser getLeader() {
		return members[0];
	}
	
	/**
	 * Removes a member of the group (can't be the leader)
	 * @param member member to be removed
	 * @return true if successfull, false, if not
	 */
	public boolean removeMember(IUser member) {		
		for(int i = 0; i < getMembers().length; i++) {
			if(getMembers()[i].equals(member)) {
				getMembers()[i] = null;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Promotes a member to the new leader (previous leader is now member)
	 * @param member the new Leader
	 * @return true, if successfull, false, if not
	 */
	public boolean promoteLeader(IUser member) {		
		for(int i = 0; i < getMembers().length; i++) {
			if(getMembers()[i].equals(member)) {
				members[i+1] = members[0];
				members[0] = member;
				return true;
			}
		}
		return false;
	}
}
