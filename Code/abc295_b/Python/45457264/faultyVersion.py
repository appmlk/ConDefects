R, C = map(int, input().split())

# 行列受け取り
B = []

for i in range(R):
  row = input()
  B.append(row)

# 新しく行列を作成
Q = [['1'] * C for r in range(R)]


for i in range(R):
  for j in range(C):
    # 数値に変換
    # 変換出来たら数値のマンハッタン距離分のマスに"."を入れる
    # 変換できなかった場合は、作成した行列が1の場合のみ、受け取った行列の値を入れる
    b = B[i][j]
    numberCheck = b.isdecimal()
    if numberCheck:
      num = int(b)
      for n in range(-num, num + 1):
        for m in range(-(num - abs(n)), num - abs(n) + 1):
          if (n + i) >= 0 and (m + j) >= 0 and (n + i) < R and (m + j) < C:
            Q[n + i][m + j] = '.'         
    else :
      if Q[i][j] == "1":
        Q[i][j] = "#"

for q in Q:
  for i in q:
    print(i, end="")
  print()