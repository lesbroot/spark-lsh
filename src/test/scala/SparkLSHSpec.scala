package com.lesbroot.sparklsh


import org.scalatest._
import sparktest.SparkTestUtils
import org.apache.spark.mllib.linalg.SparseVector


class SparkLSHSpec extends SparkTestUtils with ShouldMatchers {
  sparkTest("spark filter") {
  }

  test("random a") {
    val p = 3
    val as = (0 until 10).map(_ => Hasher.a(p))
    all (as) should (be > 0 and be < 3)
  }

  test("random b") {
    val p = 3
    val as = (0 until 10).map(_ => Hasher.b(p))
    all (as) should (be >= 0 and be < 3)
  }

  test("hash") {
    val (p, m) = (11, 7)
    val hashed = (0 until 100).map(_ => Hasher.getHasher(p, m)(4))
    all (hashed) should (be >=0 and be < m)
  }

  test("minhash") {
    val (p, m) = (11, 7)
    val hasher = Hasher.getHasher(p, m)
    val v = new SparseVector(10, (0 until 10).toArray, (0.0 until 10.0 by 1.0).toArray)
    Hasher.minHash(v, hasher) should (be >= 0 and be < m)
  }
}
