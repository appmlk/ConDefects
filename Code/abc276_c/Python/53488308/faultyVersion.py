N = int(input())
P = list(map(int, input().split()))

# どの桁から右を変更するか決め、変更する先頭を、最適なものに入れ替える
for i in range(1, N):
  if P[-(i+1)] < min(P[-i:]):
    continue
  else:
    mx = 0
    for j, c in enumerate(P[-i:]):
      if c < P[-(i+1)] and mx <c:
        mx = c  # P[-(i+1)]より小さい数の中で最大を探す
        jj = j
    P[-(i+1)], P[-(i-jj)] = P[-(i-jj)], P[-(i+1)]  # 入れ替える
  break
#変更する残りの数を最大に並べ直す。
Q = P[:-i] + sorted(P[-(i+1):], reverse=True)

print(*Q)