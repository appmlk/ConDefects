N = int(input())

def judge_num(num):
  for j in range(1,10):
    if N % j == 0:
      if num % (N / j) == 0:
        return str(j)
        break
  return "-"

ans = str()
for i in range(0, N+1):
  ans = ans + judge_num(i)
print(ans)