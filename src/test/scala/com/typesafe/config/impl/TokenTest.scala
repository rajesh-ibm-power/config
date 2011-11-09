package com.typesafe.config.impl

import org.junit.Assert._
import org.junit._

class TokenTest extends TestUtils {

    @Test
    def tokenEquality() {
        // syntax tokens
        checkEqualObjects(Tokens.START, Tokens.START)
        checkNotEqualObjects(Tokens.START, Tokens.OPEN_CURLY)

        // int
        checkEqualObjects(tokenInt(42), tokenInt(42))
        checkNotEqualObjects(tokenInt(42), tokenInt(43))

        // long
        checkEqualObjects(tokenLong(42), tokenLong(42))
        checkNotEqualObjects(tokenLong(42), tokenLong(43))

        // int and long mixed
        checkEqualObjects(tokenInt(42), tokenLong(42))
        checkNotEqualObjects(tokenInt(42), tokenLong(43))

        // boolean
        checkEqualObjects(tokenTrue, tokenTrue)
        checkNotEqualObjects(tokenTrue, tokenFalse)

        // double
        checkEqualObjects(tokenDouble(3.14), tokenDouble(3.14))
        checkNotEqualObjects(tokenDouble(3.14), tokenDouble(4.14))

        // string
        checkEqualObjects(tokenString("foo"), tokenString("foo"))
        checkNotEqualObjects(tokenString("foo"), tokenString("bar"))

        // unquoted
        checkEqualObjects(tokenUnquoted("foo"), tokenUnquoted("foo"))
        checkNotEqualObjects(tokenUnquoted("foo"), tokenUnquoted("bar"))

        // key subst
        checkEqualObjects(tokenKeySubstitution("foo"), tokenKeySubstitution("foo"))
        checkNotEqualObjects(tokenKeySubstitution("foo"), tokenKeySubstitution("bar"))

        // path subst
        checkEqualObjects(tokenPathSubstitution("foo"), tokenPathSubstitution("foo"))
        checkNotEqualObjects(tokenPathSubstitution("foo"), tokenPathSubstitution("bar"))

        // key and path not equal
        checkNotEqualObjects(tokenKeySubstitution("foo"), tokenPathSubstitution("foo"))

        // null
        checkEqualObjects(tokenNull, tokenNull)

        // newline
        checkEqualObjects(Tokens.newLine(10), Tokens.newLine(10))
        checkNotEqualObjects(Tokens.newLine(10), Tokens.newLine(11))

        // different types are not equal
        checkNotEqualObjects(tokenTrue, tokenInt(1))
        checkNotEqualObjects(tokenString("true"), tokenTrue)
    }

    @Test
    def tokenToString() {
        // just be sure toString() doesn't throw, it's for debugging
        // so its exact output doesn't matter a lot
        tokenTrue.toString()
        tokenFalse.toString()
        tokenInt(42).toString()
        tokenLong(43).toString()
        tokenDouble(3.14).toString()
        tokenNull.toString()
        tokenUnquoted("foo").toString()
        tokenString("bar").toString()
        tokenKeySubstitution("a").toString()
        tokenPathSubstitution("b").toString()
        Tokens.newLine(10).toString()
        Tokens.START.toString()
        Tokens.END.toString()
        Tokens.COLON.toString()
    }
}