a = input()
b = input()

ans = a
if int(b)%2==1:
    ans += str(int(b+"0")//2)
else:
    ans += str(int(b)//2)

print(ans)