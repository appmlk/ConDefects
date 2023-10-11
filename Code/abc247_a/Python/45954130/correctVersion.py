a=list(input())
a[3]=a[2]
a[2]=a[1]
a[1]=a[0]
a[0]="0"
print("".join(a))
