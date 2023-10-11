def ip():return int(input())
def mp():return map(int, input().split())
def lmp():return list(map(int, input().split()))
# ABC287 D 796 - Match or Not
# 英小文字と ? からなる文字列 S,T が与えられます。
# ここで、|S| > |T| が成り立ちます(文字列 X に対し、|X| で X の長さを表します)。
# また、|X| = |Y| を満たす文字列 X,Y は、次の条件を満たすとき及びそのときに限りマッチするといいます。
# ・X,Y に含まれる ? をそれぞれ独立に好きな英小文字に置き換えることで X と Y を一致させることができる
# x=0,1,…,|T| に対して次の問題を解いてください。
# S の先頭の x 文字と末尾の |T|-x 文字を順番を保ったまま連結することで得られる長さ |T| の文字列を S' とする。
# S' と T がマッチするならば Yes と、そうでなければ No と出力せよ。
# ・1 ≤ |T| < |S| ≤ 3×10^5
S = list(input())
T = list(input())
N = len(S)
M = len(T)
ind = -1
for i in range(M):
    if S[-M+i] != T[i] and S[-M+i] != '?' and T[i] != '?':
        ind = i
print('Yes' if ind == -1 else 'No')
chk = True
for i in range(M):
    if S[i] != T[i] and S[i] != '?' and T[i] != '?':
        chk = False
    print('Yes' if chk and N - M + i >= ind else 'No')