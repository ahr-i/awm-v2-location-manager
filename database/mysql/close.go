package mysql

func (mh *MysqlHandler) Close() {
	mh.DB.Close()
}
