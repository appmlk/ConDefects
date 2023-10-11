#https://atcoder.jp/contests/arc153/tasks/arc153_a

N = int(input())
 
A, B, C, D, E, F = str(100_000 + (N - 1))
ANS = "".join((A, A, B, C, D, D, E, F, E))
print(ANS)
