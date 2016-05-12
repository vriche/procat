/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.persistence;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.SupTreeList;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import javax.persistence.TableGenerator;
//import javax.persistence.SequenceGenerator;



/**
 * Entity鏀寔绫� * @author ThinkGem
 * @version 2014-05-16
 */
@SupTreeList
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 瀹炰綋缂栧彿锛堝敮涓�爣璇嗭級
	 */
	protected String id;
	
	/**
	 * 褰撳墠鐢ㄦ埛
	 */
	protected User currentUser;
	
	/**
	 * 褰撳墠瀹炰綋鍒嗛〉瀵硅薄
	 */
	protected Page<T> page;
	
	/**
	 * 鑷畾涔塖QL锛圫QL鏍囪瘑锛孲QL鍐呭锛�	 */
	protected Map<String, String> sqlMap;
	
	/**
	 * 鏄惁鏄柊璁板綍锛堥粯璁わ細false锛夛紝璋冪敤setIsNewRecord()璁剧疆鏂拌褰曪紝浣跨敤鑷畾涔塈D銆�	 * 璁剧疆涓簍rue鍚庡己鍒舵墽琛屾彃鍏ヨ鍙ワ紝ID涓嶄細鑷姩鐢熸垚锛岄渶浠庢墜鍔ㄤ紶鍏ャ�
	 */
	protected boolean isNewRecord = false;

	public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}
	
//    @SequenceGenerator(name="EMP_SEQ", allocationSize=25)
	@SupCol(isUnique="true", isHide="true")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if(currentUser == null){
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@JsonIgnore
	@XmlTransient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null){
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}
	
	/**
	 * 鎻掑叆涔嬪墠鎵ц鏂规硶锛屽瓙绫诲疄鐜�	 */
	public abstract void preInsert();
	
	/**
	 * 鏇存柊涔嬪墠鎵ц鏂规硶锛屽瓙绫诲疄鐜�	 */
	public abstract void preUpdate();
	
    /**
	 * 鏄惁鏄柊璁板綍锛堥粯璁わ細false锛夛紝璋冪敤setIsNewRecord()璁剧疆鏂拌褰曪紝浣跨敤鑷畾涔塈D銆�	 * 璁剧疆涓簍rue鍚庡己鍒舵墽琛屾彃鍏ヨ鍙ワ紝ID涓嶄細鑷姩鐢熸垚锛岄渶浠庢墜鍔ㄤ紶鍏ャ�
     * @return
     */
	public boolean getIsNewRecord() {
        return isNewRecord || StringUtils.isBlank(getId());
    }

	/**
	 * 鏄惁鏄柊璁板綍锛堥粯璁わ細false锛夛紝璋冪敤setIsNewRecord()璁剧疆鏂拌褰曪紝浣跨敤鑷畾涔塈D銆�	 * 璁剧疆涓簍rue鍚庡己鍒舵墽琛屾彃鍏ヨ鍙ワ紝ID涓嶄細鑷姩鐢熸垚锛岄渶浠庢墜鍔ㄤ紶鍏ャ�
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	/**
	 * 鍏ㄥ眬鍙橀噺瀵硅薄
	 */
	@JsonIgnore
	public Global getGlobal() {
		return Global.getInstance();
	}
	
	/**
	 * 鑾峰彇鏁版嵁搴撳悕绉�	 */
	@JsonIgnore
	public String getDbName(){
		return Global.getConfig("jdbc.type");
	}
	
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity<?> that = (BaseEntity<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
	/**
	 * 鍒犻櫎鏍囪锛�锛氭甯革紱1锛氬垹闄わ紱2锛氬鏍革紱锛�	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";
	
}
