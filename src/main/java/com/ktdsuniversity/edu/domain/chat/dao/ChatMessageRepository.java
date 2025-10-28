package com.ktdsuniversity.edu.domain.chat.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.domain.chat.vo.ChatMessageVO;
import com.ktdsuniversity.edu.domain.chat.vo.request.RequestChatMessageVO;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessageVO, String>{

	/**
	 * 채팅방 ID로 메시지를 조회
	 * ?0 --> 0(첫번째)로 들어온 parameter를 의미한다!
	 */
	@Query("{ 'CHT_RM_ID' : ?0, 'DLT_YN' : 'N' }")
	public List<ChatMessageVO> findByChtRmIdOrderByCrtDtAsc(String chtRmId);
	
	 /**
     * 채팅방의 최신 메시지 조회 (내림차순)
     */
    @Query("{ 'CHT_RM_ID': ?0, 'DLT_YN': 'N' }")
    public List<ChatMessageVO> findByChtRmIdOrderByCrtDtDesc(String chtRmId, Sort sort);
    
    /**
    * 안읽은 메시지 개수 조회
    */
   @Query(value = "{ 'CHT_RM_ID': ?0, 'USR_ID': { $ne: ?1 }, 'RD_YN': 'N', 'DLT_YN': 'N' }", count = true)
   public long countUnreadMessages(String chtRmId, String usrId);
   
   /**
    * 읽음 처리할 메시지 목록 조회
    */
   @Query("{ 'CHT_RM_ID': ?0, 'USR_ID': { $ne: ?1 }, 'RD_YN': 'N', 'DLT_YN': 'N' }")
   public List<ChatMessageVO> findUnreadMessages(String chtRmId, String usrId);
   
   /**
    * 메시지 ID로 메시지 조회
    */
   public ChatMessageVO findByChtMsgId(String chtMsgId);

   /**
    * 메시지 저장
    */
   public ChatMessageVO save(ChatMessageVO message);
}
