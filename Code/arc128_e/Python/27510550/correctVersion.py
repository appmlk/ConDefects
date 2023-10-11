N, K = map(int,input().split())

ct = list(map(int,input().split()))
  
import sys
S = sum(ct)
to_go = sum(ct)
ans = [-1]*to_go
block_idx = -(-to_go // K)
#print(block_idx, to_go)

dead = 0
while to_go > 0:
  steps = to_go % K
  if steps == 0:
    steps = K
  
  criticals = set()
  for n in range(N):
    if ct[n] > block_idx:
      print(-1)
      sys.exit()
    if ct[n] == block_idx:
      criticals.add(n)
  
  ans_idx = - (block_idx - 1) * K - steps
  #print(block_idx, steps)
  
  # 不可能
  if len(criticals) > steps - dead:
    print(-1)
    sys.exit()
  
  #print(block_idx, criticals, steps, dead, ans_idx)
  
  for j in range(steps - dead):
    if len(criticals) == steps - dead - j: # 制限に達した時
      criticals = list(criticals)
      criticals.sort()
      while ans_idx < 0: # 最後まで埋めなければならない
        for i in range(ans_idx, ans_idx + len(criticals)):
          ans[i] = criticals[i - ans_idx]
        ans_idx += K
      dead += len(criticals)
      for c in criticals:
        ct[c] = 0
      break # このブロックの処理を終える
    
    used = set(ans[max(-S, ans_idx - K + 1): ans_idx])
    #print(used, max(-S, ans_idx - K), ans_idx)
    for num_now in range(N):
      if ct[num_now] and (num_now not in used): # num_nowは残っている数のうち最小のものとしたい
        #print(ans_idx)
        ans[ans_idx] = num_now
        ct[num_now] -= 1
        if num_now in criticals: criticals.remove(num_now)
        break
    
    ans_idx += 1
  
  to_go -= steps
  block_idx -= 1
  
#print(ans)
ans = [a + 1 for a in ans]
print(*ans)