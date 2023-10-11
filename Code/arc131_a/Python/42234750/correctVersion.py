a = input()
b = input()

ans = a+"0"
if int(b)%2==1:
    ans += str(int(b+"0")//2)
else:
    ans += str(int(b)//2)

print(ans)