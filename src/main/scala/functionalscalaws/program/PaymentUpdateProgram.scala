package functionalscalaws.program

import functionalscalaws.algebra.AmountValidationAlg
import functionalscalaws.domain._
import functionalscalaws.domain.db._
import functionalscalaws.services.db.RepositoryAlg
import zio._

trait PaymentUpdateProgram {
  def update(updateAmount: AmountUpdate): IO[ServiceError, PaymentData]

}
object PaymentUpdateProgram {

  def live: URLayer[Has[AmountValidationAlg] with Has[RepositoryAlg.PaymentRepository], Has[
    PaymentUpdateProgram
  ]] =
    (for {
      amountValidation <- ZIO.service[AmountValidationAlg]
      repo             <- ZIO.service[RepositoryAlg.PaymentRepository]
    } yield Interpreter(amountValidation, repo)).toLayer

  def update(
      updateAmount: AmountUpdate
  ): ZIO[Has[PaymentUpdateProgram], ServiceError, PaymentData] =
    ZIO.accessM[Has[PaymentUpdateProgram]](_.get update updateAmount)

  final case class Interpreter(
      amountValidation: AmountValidationAlg,
      repo: RepositoryAlg.PaymentRepository
  ) extends PaymentUpdateProgram {
    def update(updateAmount: AmountUpdate): zio.IO[ServiceError, PaymentData] =
      for {
        _      <- amountValidation.validate(updateAmount.amount)
        result <- repo.update(updateAmount)
      } yield result
  }
}
