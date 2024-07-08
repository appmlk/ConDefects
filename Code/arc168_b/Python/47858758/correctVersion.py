from collections import defaultdict
n = int(input())
a = list(map(int, input().split()))

xor_sum = 0
cnt = defaultdict(lambda: 0)
for elem in a:
  xor_sum = xor_sum ^ elem
  cnt[elem] += 1

if xor_sum > 0:
  print(-1)
  exit(0)

elem_and_cnt = list(cnt.items())
elem_and_cnt.sort(key=lambda elem: elem[0], reverse=True)

for elem, cnt in elem_and_cnt:
  if cnt % 2 == 1:
    print(elem - 1)
    exit(0)
    
print(0)