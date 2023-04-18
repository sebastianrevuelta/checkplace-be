class Product(models.Model):
    title = models.CharField(max_length=64)
    description = models.TextField()

    price = models.DecimalField(max_digits=6, decimal_places=2)
