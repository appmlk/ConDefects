arr = list(map(int, input().split()))
fl = True
if arr[0] < 100 or arr[-1] > 675:
    fl = False
for i in range(1, 8):
    if arr[i - 1] > arr[i]:
        fl = False
    if arr[i - 1] % 25 != 0 or arr[i] % 25 != 0:
        fl = False

print("Yes" if fl else "No")