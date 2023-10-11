def main():
  N = int(input())
  S = input()
  S = [S[:N], S[N:2*N], S[2*N:]]
  d = [{"A": S[i].count("A"), "B": S[i].count("B"), "C": S[i].count("C")} for i in range(3)]
  idx = [{"A": 0, "B": 0, "C": 0} for i in range(3)]
  perm = ["ABC", "ACB", "BCA", "BAC", "CAB", "CBA"]
  ans = [[0] * N for i in range(3)]
  for k, p in enumerate(perm):
    cnt = N
    for i in range(3):
      cnt = min(cnt, d[i][p[i]])
    for i in range(3):
      t = cnt
      for j in range(idx[i][p[i]], N):
        if t == 0: break
        idx[i][p[i]] += 1
        if S[i][j] == p[i]:
          ans[i][j] = k+1
          d[i][p[i]] -= 1
          t -= 1
  print("".join(map(str, ans[0] + ans[1] + ans[2])))

if __name__=="__main__":
  main()