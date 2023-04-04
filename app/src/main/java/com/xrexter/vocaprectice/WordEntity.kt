package com.xrexter.vocaprectice

import androidx.room.*

@Entity
data class UserWordSet(
	@PrimaryKey val idx: Int,
	val english: String?,
	val korean: String?,
	val corrects: Int,
	val wrongs: Int
)

@Dao
interface UserWordInterface {
	@Query("select * from userwordset")
	fun getAll(): List<UserWordSet>

	@Query("select idx from userwordset order by idx desc limit 1")
	fun getLastIndex(): Int

	@Insert
	fun insert(userWordSet: UserWordSet)
}

@Database(entities = [UserWordSet::class], version = 1)
abstract class UserWordDatabase: RoomDatabase() {
	abstract fun userWordInterface(): UserWordInterface
}