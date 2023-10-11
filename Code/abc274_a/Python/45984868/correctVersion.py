from decimal import Decimal, ROUND_UP

a,b = map(int,input().split())
print(Decimal(str(b/a)).quantize(Decimal(10) ** -3))