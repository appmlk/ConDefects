arr = input().split(' ')
arr = sorted(arr)
if int(arr[0]) + int(arr[1]) >= int(arr[2]):
    print(arr[2])
else:
    print("-1")
