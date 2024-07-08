S=input()
num=0
if "ABC316"==S:
    print("No")
    exit()
for x in range(1,350):
    x=str(x).zfill(3)
    num=('ABC'+x)
    if num==S:
        print("Yes")
        break
else:
    print("No")