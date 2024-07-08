n, m = map(int, input().split())
s = list(input())

ans = 0
cnt = 0
muji = 0

for c in s:
  match c:
    case "0":
      cnt =0
      muji = m
    case "1":
      if muji == 0:
        cnt += 1
      else:
        muji -= 1
    case "2":
      cnt += 1
  ans = max(cnt, ans)
print(ans)