N, Q = map(int, input().split())

# 答えの候補
last_ans = 0

# 列の先頭
top = 0

# 受付に呼ばれたが、行ってない
ready = [False] * N

for i in range(Q):
  t = input()
  if t == "1":
    ready[top] = True
    top += 1
  elif t == "3":
    for j in range(last_ans, N):
      if ready[j] == True:
        print(j + 1)
        last_ans = j + 1
        break
  else:
    x = int(t[2:])
    ready[x - 1] = False