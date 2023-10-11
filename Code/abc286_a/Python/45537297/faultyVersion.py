# 入力の受け取り
N,P,Q,R,S=map(int, input().split())
# 最初に[0]を埋めて番号をずらす
A=[0]+list(map(int, input().split()))
# 1~P-1
A1=A[1:P]
# P~Q
A2=A[P:Q+1]
# Q+1~R-1
A3=A[Q+1:R]
# R~S
A4=A[R:S+1]
# S+1~N
A5=A[S+1:N+1]

# つなげる
B=A1+A4+A3+A2+A5

# 出力(「*」をつけるとかっこなしで出力できる)
print(B)