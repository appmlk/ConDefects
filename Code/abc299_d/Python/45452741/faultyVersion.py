n = int(input())

high = n
low = -1

while low + 1 < high:
    mid = (low + high) // 2
    print(f'? {mid}')
    s = input()
    if s == "0":
        low = mid
    else:
        high = mid

print(f"! {low + 1}")