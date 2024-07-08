N = int(input())
n = 10**6
for i in range(n, 1, -1):
  k = i **3
  k_str = str(k)
  if (k <= N) and (k_str == k_str[::-1]):
    print(k)
    exit()
print(1)