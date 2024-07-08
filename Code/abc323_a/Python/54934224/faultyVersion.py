S = input().strip()

for i in range(1, 9):
  if S[2 * i - 1] != 0:
    print("No")
    exit()
print("Yes")